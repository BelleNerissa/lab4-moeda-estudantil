import { BrowserRouter, Route, Routes } from "react-router-dom";
import Aluno from "../pages/Aluno/aluno";
import CadastrarAluno from "../pages/CadastrarAluno/cadastrarAluno";
import CadastrarEmpresa from "../pages/CadastrarEmpresa/cadastrarEmpresa";
import EditarAluno from "../pages/EditarAluno/editarAluno";
import EditarEmpresa from "../pages/EditarEmpresa/editarEmpresa";
import Empresa from "../pages/Empresa/empresa";
import Lista from "../pages/Lista/lista";

const Rotas = () => (
  <BrowserRouter>
    <Routes>
      <Route path="/" caseSensitive={false} element={<Lista />} />
    </Routes>
    <Routes>
      <Route path="/aluno/:id" caseSensitive={false} element={<Aluno />} />
    </Routes>
    <Routes>
      <Route path="/empresa/:id" caseSensitive={false} element={<Empresa />} />
    </Routes>
    <Routes>
      <Route
        path="/cadastro/aluno"
        caseSensitive={false}
        element={<CadastrarAluno />}
      />
    </Routes>
    <Routes>
      <Route
        path="/cadastro/empresa"
        caseSensitive={false}
        element={<CadastrarEmpresa />}
      />
    </Routes>
    <Routes>
      <Route
        path="/editar/aluno/:id"
        caseSensitive={false}
        element={<EditarAluno />}
      />
    </Routes>
    <Routes>
      <Route
        path="/editar/empresa/:id"
        caseSensitive={false}
        element={<EditarEmpresa />}
      />
    </Routes>
  </BrowserRouter>
);

export default Rotas;
