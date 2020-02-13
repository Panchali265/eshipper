import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { WebNotificationUpdateComponent } from 'app/entities/web-notification/web-notification-update.component';
import { WebNotificationService } from 'app/entities/web-notification/web-notification.service';
import { WebNotification } from 'app/shared/model/web-notification.model';

describe('Component Tests', () => {
  describe('WebNotification Management Update Component', () => {
    let comp: WebNotificationUpdateComponent;
    let fixture: ComponentFixture<WebNotificationUpdateComponent>;
    let service: WebNotificationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [WebNotificationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WebNotificationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WebNotificationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WebNotificationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WebNotification(123);
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
        const entity = new WebNotification();
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
