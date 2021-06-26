import { ChartDataSets } from 'chart.js';
import { Label } from "ng2-charts";
import { Produto } from '../../produto/models/produto.models';

export interface InfoChartViewModel {
  loading: boolean;
  labels: Label[],
  datasets: ChartDataSets[],
}
