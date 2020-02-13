import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EmailNotificationComponent } from 'app/entities/email-notification/email-notification.component';
import { EmailNotificationService } from 'app/entities/email-notification/email-notification.service';
import { EmailNotification } from 'app/shared/model/email-notification.model';

describe('Component Tests', () => {
  describe('EmailNotification Management Component', () => {
    let comp: EmailNotificationComponent;
    let fixture: ComponentFixture<EmailNotificationComponent>;
    let service: EmailNotificationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EmailNotificationComponent]
      })
        .overrideTemplate(EmailNotificationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EmailNotificationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EmailNotificationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EmailNotification(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.emailNotifications && comp.emailNotifications[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
