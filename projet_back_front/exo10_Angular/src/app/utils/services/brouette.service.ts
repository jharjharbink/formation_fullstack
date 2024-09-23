import { Injectable } from '@angular/core';
import {Brouette} from "../types/brouette";

@Injectable({
  providedIn: 'root'
})
export class BrouetteService {

  brouettes: Brouette[] = [];
  brouettePanier: Brouette[] = [];

  constructor() {
    const brouetteStored = localStorage.getItem('brouettes');
    if(brouetteStored) {
      this.brouettes = JSON.parse(brouetteStored)
    }

    const brouettePanierStored = localStorage.getItem('brouettesPanier');
    if(brouettePanierStored) {
      this.brouettePanier = JSON.parse(brouettePanierStored)
    }
  }

  addBrouette(brouette: Brouette) {
    this.brouettes.push(brouette);
    localStorage.setItem('brouettes', JSON.stringify(this.brouettes))
  }

  removeBrouette(brouetteToDelete: Brouette) {
    this.brouettes.splice(this.brouettes.indexOf(brouetteToDelete), 1);
    localStorage.setItem('brouettes', JSON.stringify(this.brouettes))
  }

  addBrouettePanier(brouetteToAdd: Brouette) {

    // retirer 1 du stock
    const indexBrouette:number = this.brouettes.indexOf(brouetteToAdd)
    if(this.brouettes[indexBrouette].quantite >0){
      this.brouettes[indexBrouette].quantite -= 1;

      // ajouter 1 au panier
      let alreadyInPanier = false
      this.brouettePanier.forEach((b) => {
        if(b.nom === brouetteToAdd.nom && b.description === brouetteToAdd.description && b.prix === brouetteToAdd.prix){
          b.quantite ++;
          alreadyInPanier = true;
        }
      });

      if (!alreadyInPanier){
        const newBrouette = Object.assign({}, brouetteToAdd);
        newBrouette.quantite = 1;
        this.brouettePanier.push(newBrouette);
      }

      // toucher aux deux local storages
      localStorage.setItem('brouettes', JSON.stringify(this.brouettes))
      localStorage.setItem('brouettesPanier', JSON.stringify(this.brouettePanier))

    } else {
      alert("En cours de réapprovisionnement");
    }
  }

  removeFromCard(brouetteToRemove: Brouette) {
    const index = this.brouettePanier.indexOf(brouetteToRemove)

    this.brouettePanier[index].quantite --;
    if(this.brouettePanier[index].quantite === 0){
      this.brouettePanier.splice(index, 1);
    }

    // if (this.brouettePanier[index].quantite === 1) {
    //   this.brouettePanier.splice(index, 1);
    // } else {
    //   this.brouettePanier[index].quantite --;
    // }
    let brouetteStock: Brouette = this.brouettes.find(br => br.nom === brouetteToRemove.nom) as Brouette;
    brouetteStock.quantite += 1;

    localStorage.setItem('brouettesPanier', JSON.stringify(this.brouettePanier));
    localStorage.setItem('brouettes', JSON.stringify(this.brouettes))

  }

}
