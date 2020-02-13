import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { UserNotificationSettingsUpdateComponent } from 'app/entities/user-notification-settings/user-notification-settings-update.component';
import { UserNotificationSettingsService } from 'app/entities/user-notification-settings/user-notification-settings.service';
import { UserNotificationSettings } from 'app/shared/model/user-notification-settings.model';

describe('Component Tests', () => {
  describe('UserNotificationSettings Management Update Component', () => {
    let comp: UserNotificationSettingsUpdateComponent;
    let fixture: ComponentFixture<UserNotificationSettingsUpdateComponent>;
    let service: UserNotificationSettingsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [UserNotificationSettingsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(UserNotificationSettingsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserNotificationSettingsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserNotificationSettingsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserNotificationSettings(123);
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
        const entity = new UserNotificationSettings();
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
