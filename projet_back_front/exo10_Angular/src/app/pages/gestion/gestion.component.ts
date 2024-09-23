import {Component, OnInit} from '@angular/core';
import {BrouetteService} from "../../utils/services/brouette.service";
import {Brouette} from "../../utils/types/brouette";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {TableauComponent} from "../../components/tableau/tableau.component";

@Component({
  selector: 'app-gestion',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    TableauComponent
  ],
  templateUrl: './gestion.component.html',
  styleUrl: './gestion.component.css'
})
export class GestionComponent {

  constructor(private brouetteService: BrouetteService) {
    this.brouettes = brouetteService.brouettes;
  }


  brouettes: Brouette[] = [];

  brouette_form = new FormGroup({
    nom: new FormControl(''),
    description: new FormControl(''),
    prix: new FormControl(0),
    quantite: new FormControl(1),
  })

  saveBrouette() {
    this.brouetteService.addBrouette(this.brouette_form.value as Brouette);
  }

  delete(brouetteToDelete: Brouette) {
    this.brouetteService.removeBrouette(brouetteToDelete);

  }

}
