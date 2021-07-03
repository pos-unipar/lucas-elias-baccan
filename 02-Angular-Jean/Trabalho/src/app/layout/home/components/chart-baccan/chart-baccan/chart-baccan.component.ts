import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ChartType } from 'chart.js';
import { Color } from 'ng2-charts';
import { InfoChartViewModel } from '../../../models/home.model';

@Component({
  selector: 'app-chart-baccan',
  templateUrl: './chart-baccan.component.html',
  styleUrls: ['./chart-baccan.component.scss']
})
export class ChartBaccanComponent implements OnInit {

  @Input()
  chartTitle?: string;

  @Input()
  chartData: InfoChartViewModel = { loading: true, datasets: [], labels: [] };

  @Input()
  chartType: ChartType = 'line';

  @Input()
  colors: Color[] = [];

  @Output()
  onRefresh: EventEmitter<void> = new EventEmitter<void>();

  constructor() { }

  ngOnInit(): void {
  }

  onRefreshChart(): void {
    this.onRefresh.emit();
  }

}
