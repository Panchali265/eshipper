import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { NotificationMethodComponent } from 'app/entities/notification-method/notification-method.component';
import { NotificationMethodService } from 'app/entities/notification-method/notification-method.service';
import { NotificationMethod } from 'app/shared/model/notification-method.model';

describe('Component Tests', () => {
  describe('NotificationMethod Management Component', () => {
    let comp: NotificationMethodComponent;
    let fixture: ComponentFixture<NotificationMethodComponent>;
    let service: NotificationMethodService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [NotificationMethodComponent]
      })
        .overrideTemplate(NotificationMethodComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NotificationMethodComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NotificationMethodService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new NotificationMethod(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.notificationMethods && comp.notificationMethods[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
