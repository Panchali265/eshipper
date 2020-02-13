import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { UserNotificationSettingsDetailComponent } from 'app/entities/user-notification-settings/user-notification-settings-detail.component';
import { UserNotificationSettings } from 'app/shared/model/user-notification-settings.model';

describe('Component Tests', () => {
  describe('UserNotificationSettings Management Detail Component', () => {
    let comp: UserNotificationSettingsDetailComponent;
    let fixture: ComponentFixture<UserNotificationSettingsDetailComponent>;
    const route = ({ data: of({ userNotificationSettings: new UserNotificationSettings(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [UserNotificationSettingsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(UserNotificationSettingsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserNotificationSettingsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load userNotificationSettings on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.userNotificationSettings).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
