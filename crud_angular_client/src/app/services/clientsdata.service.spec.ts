import { TestBed } from '@angular/core/testing';

import { ClientsdataService } from './clientsdata.service';

describe('ClientsdataService', () => {
  let service: ClientsdataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientsdataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
