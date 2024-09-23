import {Component, EventEmitter, Input, Output, TemplateRef} from '@angular/core';
import {Brouette} from "../../utils/types/brouette";
import {NgClass, NgTemplateOutlet} from "@angular/common";

@Component({
  selector: 'app-tableau',
  standalone: true,
  imports: [
    NgTemplateOutlet,
    NgClass
  ],
  templateUrl: './tableau.component.html',
  styleUrl: './tableau.component.css'
})
export class TableauComponent {

@Input() brouettes: Brouette[] = [];
@Input() affichage!: TemplateRef<string>;

@Output() clickButton = new EventEmitter<Brouette>;

onClick (brouette: Brouette) {
  // console.log(`Tableau - brouettes: ${JSON.stringify(this.brouettes)}`)
  // console.log(`Tableau - brouette: ${JSON.stringify(brouette)}`)
  // console.log(`Tableau - index brouette: ${this.brouettes.indexOf(brouette)}`)
  this.clickButton.emit(brouette)
}

}
