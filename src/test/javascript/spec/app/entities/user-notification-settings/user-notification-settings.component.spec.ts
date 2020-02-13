import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { UserNotificationSettingsComponent } from 'app/entities/user-notification-settings/user-notification-settings.component';
import { UserNotificationSettingsService } from 'app/entities/user-notification-settings/user-notification-settings.service';
import { UserNotificationSettings } from 'app/shared/model/user-notification-settings.model';

describe('Component Tests', () => {
  describe('UserNotificationSettings Management Component', () => {
    let comp: UserNotificationSettingsComponent;
    let fixture: ComponentFixture<UserNotificationSettingsComponent>;
    let service: UserNotificationSettingsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [UserNotificationSettingsComponent]
      })
        .overrideTemplate(UserNotificationSettingsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserNotificationSettingsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserNotificationSettingsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UserNotificationSettings(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.userNotificationSettings && comp.userNotificationSettings[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
