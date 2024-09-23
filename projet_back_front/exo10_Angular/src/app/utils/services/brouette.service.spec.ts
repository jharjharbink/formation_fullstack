import { TestBed } from '@angular/core/testing';

import { BrouetteService } from './brouette.service';

describe('BrouetteService', () => {
  let service: BrouetteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BrouetteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
