import { Component } from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {Brouette} from "../../utils/types/brouette";
import {TableauComponent} from "../../components/tableau/tableau.component";
import {BrouetteService} from "../../utils/services/brouette.service";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule,
    TableauComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  search: string = '';

  constructor(private brouetteService: BrouetteService) {
    this.brouettes = brouetteService.brouettes;
  }

  brouettes: Brouette[] = [];

  addToCard(brouetteToAddToCard: Brouette) {
    this.brouetteService.addBrouettePanier(brouetteToAddToCard);
  }

  sortByPrice(){
    this.brouettes.sort((a, b) => a.prix - b.prix);
  }
}
