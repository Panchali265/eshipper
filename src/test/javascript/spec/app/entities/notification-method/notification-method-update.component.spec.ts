import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { NotificationMethodUpdateComponent } from 'app/entities/notification-method/notification-method-update.component';
import { NotificationMethodService } from 'app/entities/notification-method/notification-method.service';
import { NotificationMethod } from 'app/shared/model/notification-method.model';

describe('Component Tests', () => {
  describe('NotificationMethod Management Update Component', () => {
    let comp: NotificationMethodUpdateComponent;
    let fixture: ComponentFixture<NotificationMethodUpdateComponent>;
    let service: NotificationMethodService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [NotificationMethodUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(NotificationMethodUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NotificationMethodUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NotificationMethodService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NotificationMethod(123);
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
        const entity = new NotificationMethod();
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
