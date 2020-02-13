import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { NotificationMethodDetailComponent } from 'app/entities/notification-method/notification-method-detail.component';
import { NotificationMethod } from 'app/shared/model/notification-method.model';

describe('Component Tests', () => {
  describe('NotificationMethod Management Detail Component', () => {
    let comp: NotificationMethodDetailComponent;
    let fixture: ComponentFixture<NotificationMethodDetailComponent>;
    const route = ({ data: of({ notificationMethod: new NotificationMethod(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [NotificationMethodDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(NotificationMethodDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NotificationMethodDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load notificationMethod on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.notificationMethod).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
