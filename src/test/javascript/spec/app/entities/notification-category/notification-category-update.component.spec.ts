import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { NotificationCategoryUpdateComponent } from 'app/entities/notification-category/notification-category-update.component';
import { NotificationCategoryService } from 'app/entities/notification-category/notification-category.service';
import { NotificationCategory } from 'app/shared/model/notification-category.model';

describe('Component Tests', () => {
  describe('NotificationCategory Management Update Component', () => {
    let comp: NotificationCategoryUpdateComponent;
    let fixture: ComponentFixture<NotificationCategoryUpdateComponent>;
    let service: NotificationCategoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [NotificationCategoryUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(NotificationCategoryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NotificationCategoryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NotificationCategoryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NotificationCategory(123);
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
        const entity = new NotificationCategory();
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
