import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { NotificationCategoryComponent } from 'app/entities/notification-category/notification-category.component';
import { NotificationCategoryService } from 'app/entities/notification-category/notification-category.service';
import { NotificationCategory } from 'app/shared/model/notification-category.model';

describe('Component Tests', () => {
  describe('NotificationCategory Management Component', () => {
    let comp: NotificationCategoryComponent;
    let fixture: ComponentFixture<NotificationCategoryComponent>;
    let service: NotificationCategoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [NotificationCategoryComponent]
      })
        .overrideTemplate(NotificationCategoryComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NotificationCategoryComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NotificationCategoryService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new NotificationCategory(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.notificationCategories && comp.notificationCategories[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
