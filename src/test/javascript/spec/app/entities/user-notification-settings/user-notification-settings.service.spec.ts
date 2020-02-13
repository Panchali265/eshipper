import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UserNotificationSettingsService } from 'app/entities/user-notification-settings/user-notification-settings.service';
import { IUserNotificationSettings, UserNotificationSettings } from 'app/shared/model/user-notification-settings.model';

describe('Service Tests', () => {
  describe('UserNotificationSettings Service', () => {
    let injector: TestBed;
    let service: UserNotificationSettingsService;
    let httpMock: HttpTestingController;
    let elemDefault: IUserNotificationSettings;
    let expectedResult: IUserNotificationSettings | IUserNotificationSettings[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(UserNotificationSettingsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new UserNotificationSettings(0, false, false, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a UserNotificationSettings', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new UserNotificationSettings()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a UserNotificationSettings', () => {
        const returnedFromService = Object.assign(
          {
            web: true,
            email: true,
            configurableMap: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of UserNotificationSettings', () => {
        const returnedFromService = Object.assign(
          {
            web: true,
            email: true,
            configurableMap: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a UserNotificationSettings', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
