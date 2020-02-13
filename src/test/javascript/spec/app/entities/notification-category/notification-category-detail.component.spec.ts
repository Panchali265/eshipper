import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { NotificationCategoryDetailComponent } from 'app/entities/notification-category/notification-category-detail.component';
import { NotificationCategory } from 'app/shared/model/notification-category.model';

describe('Component Tests', () => {
  describe('NotificationCategory Management Detail Component', () => {
    let comp: NotificationCategoryDetailComponent;
    let fixture: ComponentFixture<NotificationCategoryDetailComponent>;
    const route = ({ data: of({ notificationCategory: new NotificationCategory(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [NotificationCategoryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(NotificationCategoryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NotificationCategoryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load notificationCategory on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.notificationCategory).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
