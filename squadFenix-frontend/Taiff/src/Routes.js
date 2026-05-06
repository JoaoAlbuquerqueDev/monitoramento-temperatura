import React from 'react';
import { Routes, Route } from 'react-router-dom';

import Home from './pages/Home';
import CadPosicoes from './pages/CadPosicoes';
import Tabela from './pages/Tabela';
import Grafico from './pages/Grafico';
import InfoTeste from './pages/InfoTeste';
import CadTeste from './pages/CadTeste';
import CadZeroPeca from './pages/CadZeroPeca';

export default function RoutesApp() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/tabela" element={<Tabela />} />
      <Route path="/cadPosicoes" element={<CadPosicoes />} />
      <Route path="/grafico" element={<Grafico />} />
      <Route path="/infoTeste" element={<InfoTeste />} />
      <Route path="/cadTeste" element={<CadTeste />} />
      <Route path="/cadZeroPeca" element={<CadZeroPeca />} />
      <Route path="/relatorio" element = {<InfoTeste/>} />
    </Routes>
  );
}