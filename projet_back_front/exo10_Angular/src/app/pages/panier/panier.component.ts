import { Component } from '@angular/core';
import {TableauComponent} from "../../components/tableau/tableau.component";
import {BrouetteService} from "../../utils/services/brouette.service";
import {Brouette} from "../../utils/types/brouette";

@Component({
  selector: 'app-panier',
  standalone: true,
  imports: [
    TableauComponent
  ],
  templateUrl: './panier.component.html',
  styleUrl: './panier.component.css'
})
export class PanierComponent {

  constructor(private brouetteService: BrouetteService) {
    this.brouettePanier = this.brouetteService.brouettePanier;
  }

  brouettePanier: Brouette[] = [];

  removeBrouetteFromCard(brouette: Brouette) {
    this.brouetteService.removeFromCard(brouette);
  }




}
