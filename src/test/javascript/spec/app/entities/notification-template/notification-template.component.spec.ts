import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { NotificationTemplateComponent } from 'app/entities/notification-template/notification-template.component';
import { NotificationTemplateService } from 'app/entities/notification-template/notification-template.service';
import { NotificationTemplate } from 'app/shared/model/notification-template.model';

describe('Component Tests', () => {
  describe('NotificationTemplate Management Component', () => {
    let comp: NotificationTemplateComponent;
    let fixture: ComponentFixture<NotificationTemplateComponent>;
    let service: NotificationTemplateService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [NotificationTemplateComponent]
      })
        .overrideTemplate(NotificationTemplateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NotificationTemplateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NotificationTemplateService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new NotificationTemplate(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.notificationTemplates && comp.notificationTemplates[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
