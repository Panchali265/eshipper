import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { NotificationTemplateDetailComponent } from 'app/entities/notification-template/notification-template-detail.component';
import { NotificationTemplate } from 'app/shared/model/notification-template.model';

describe('Component Tests', () => {
  describe('NotificationTemplate Management Detail Component', () => {
    let comp: NotificationTemplateDetailComponent;
    let fixture: ComponentFixture<NotificationTemplateDetailComponent>;
    const route = ({ data: of({ notificationTemplate: new NotificationTemplate(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [NotificationTemplateDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(NotificationTemplateDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NotificationTemplateDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load notificationTemplate on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.notificationTemplate).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
