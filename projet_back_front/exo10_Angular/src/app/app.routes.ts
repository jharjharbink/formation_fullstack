import { Routes } from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {PanierComponent} from "./pages/panier/panier.component";
import {GestionComponent} from "./pages/gestion/gestion.component";

export const routes: Routes = [
  { path: '', component: HomeComponent },
  {path: 'panier', component: PanierComponent},
  {path: 'gestion', component: GestionComponent},
];
