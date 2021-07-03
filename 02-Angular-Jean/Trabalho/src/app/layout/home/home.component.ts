import { Component, OnInit } from '@angular/core';
import { Color } from 'ng2-charts';
import { Grupo } from '../grupo/models/grupo.models';
import { GrupoService } from '../grupo/services/grupo.service';
import { Produto } from '../produto/models/produto.models';
import { ProdutoService } from '../produto/services/produto.service';
import { InfoChartViewModel } from './models/home.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public produtosGrupo: InfoChartViewModel =
    {
      loading: true,
      datasets: [],
      labels: []
    };

  public produtosStatus: InfoChartViewModel =
    {
      loading: true,
      datasets: [],
      labels: []
    };

  constructor(
    private produtoService: ProdutoService,
    private grupoService: GrupoService
  ) { }

  ngOnInit(): void {
    this.buscarDadosAsync();
  }

  async buscarDadosAsync(): Promise<void> {

    this.produtosGrupo = { loading: true, datasets: [], labels: [] };
    this.produtosStatus = { loading: true, datasets: [], labels: [] };

    try {
      const produtos = await this.produtoService.buscarTodos().toPromise();
      const grupos = await this.grupoService.buscarTodos().toPromise();

      this.montaDadosGraficos(produtos, grupos);

    } catch (error) {
    } finally {
      this.produtosGrupo.loading = false;
      this.produtosStatus.loading = false;
    }
  }

  private montaDadosGraficos(produtos: Produto[], grupos: Grupo[]): void {

    this.calcularGrupoProdutos(produtos, grupos);
    this.calcularProdutosStatus(produtos);

  }

  private calcularGrupoProdutos(produtos: Produto[], grupos: Grupo[]) {
    let mapGrupos = new Map();

    produtos.forEach(prod => {
      let grupoDescricao = grupos.filter(grupo => (grupo.id == prod.groupId))[0].descricao;

      if (mapGrupos.has(grupoDescricao)) {
        let count: number = mapGrupos.get(grupoDescricao);
        mapGrupos.set(grupoDescricao, count + 1);
      } else {
        mapGrupos.set(grupoDescricao, 1);
      }
    });

    let labels = [];
    let valores = [];

    for (let key of mapGrupos.keys()) {
      labels.push(key);
      valores.push(mapGrupos.get(key));
    }

    this.produtosGrupo.datasets = [
      { data: valores, label: 'Valor' }
    ];
    this.produtosGrupo.labels = labels;
    return { valores, labels };
  }

  private calcularProdutosStatus(produtos: Produto[]) {
    let mapStatus = new Map();

    produtos.forEach(prod => {
      let status = (""+prod.ativo) == "true" ? "Ativo" : "Inativo";

      if (mapStatus.has(status)) {
        let count: number = mapStatus.get(status);
        mapStatus.set(status, count + 1);
      } else {
        mapStatus.set(status, 1);
      }
    });
    let labels = [];
    let valores = [];

    for (let key of mapStatus.keys()) {
      labels.push(key);
      valores.push(mapStatus.get(key));
    }

    this.produtosStatus.datasets = [
      { data: valores, label: 'Valor' }
    ];
    this.produtosStatus.labels = labels;
    return { valores, labels };
  }

  public doughnutChartColors: Color[] = [
    { backgroundColor: ["#32a852", "#b33015"] },
  ];
}