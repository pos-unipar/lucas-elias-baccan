import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { ChartBaccanComponent } from './components/chart-baccan/chart-baccan/chart-baccan.component';
import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [
    FormsModule,
    CommonModule,
    HomeRoutingModule,
    ChartsModule
  ],
  declarations: [
    HomeComponent,
    ChartBaccanComponent,
  ],
  exports: [

  ]
})
export class HomeModule { }
