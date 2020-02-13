import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EmailNotificationUpdateComponent } from 'app/entities/email-notification/email-notification-update.component';
import { EmailNotificationService } from 'app/entities/email-notification/email-notification.service';
import { EmailNotification } from 'app/shared/model/email-notification.model';

describe('Component Tests', () => {
  describe('EmailNotification Management Update Component', () => {
    let comp: EmailNotificationUpdateComponent;
    let fixture: ComponentFixture<EmailNotificationUpdateComponent>;
    let service: EmailNotificationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EmailNotificationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EmailNotificationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EmailNotificationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EmailNotificationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EmailNotification(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new EmailNotification();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
