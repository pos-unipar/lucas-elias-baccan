export interface Produto {
  id?: number;
  descricao: string; //min = 5 | max = 100
  ativo: boolean;
  groupId: number; // id do grupo
  preco: Preco;
  estoque: number; //min = 0 | max = 999999999
  dateInsert: Date;
  dateUpdate: Date;
}

export interface Preco {
  venda: number; //min = 0.01 | max = 999999999
  compra: number; //min = 0.01 | max = 999999999
}
