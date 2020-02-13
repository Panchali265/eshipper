import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { WebNotificationDetailComponent } from 'app/entities/web-notification/web-notification-detail.component';
import { WebNotification } from 'app/shared/model/web-notification.model';

describe('Component Tests', () => {
  describe('WebNotification Management Detail Component', () => {
    let comp: WebNotificationDetailComponent;
    let fixture: ComponentFixture<WebNotificationDetailComponent>;
    const route = ({ data: of({ webNotification: new WebNotification(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [WebNotificationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WebNotificationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WebNotificationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load webNotification on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.webNotification).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
