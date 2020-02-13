import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { NotificationTemplateUpdateComponent } from 'app/entities/notification-template/notification-template-update.component';
import { NotificationTemplateService } from 'app/entities/notification-template/notification-template.service';
import { NotificationTemplate } from 'app/shared/model/notification-template.model';

describe('Component Tests', () => {
  describe('NotificationTemplate Management Update Component', () => {
    let comp: NotificationTemplateUpdateComponent;
    let fixture: ComponentFixture<NotificationTemplateUpdateComponent>;
    let service: NotificationTemplateService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [NotificationTemplateUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(NotificationTemplateUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NotificationTemplateUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NotificationTemplateService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NotificationTemplate(123);
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
        const entity = new NotificationTemplate();
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
