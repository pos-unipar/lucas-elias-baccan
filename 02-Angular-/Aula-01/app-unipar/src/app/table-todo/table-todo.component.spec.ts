import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableTodoComponent } from './table-todo.component';

describe('TableTodoComponent', () => {
  let component: TableTodoComponent;
  let fixture: ComponentFixture<TableTodoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableTodoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TableTodoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
