import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EmailNotificationDetailComponent } from 'app/entities/email-notification/email-notification-detail.component';
import { EmailNotification } from 'app/shared/model/email-notification.model';

describe('Component Tests', () => {
  describe('EmailNotification Management Detail Component', () => {
    let comp: EmailNotificationDetailComponent;
    let fixture: ComponentFixture<EmailNotificationDetailComponent>;
    const route = ({ data: of({ emailNotification: new EmailNotification(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EmailNotificationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EmailNotificationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EmailNotificationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load emailNotification on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.emailNotification).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
