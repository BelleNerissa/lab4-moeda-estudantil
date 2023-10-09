import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import Header from "../Components/Header/header";
import CadastrarAluno from "../pages/Forms/CadastrarAluno/cadastrarAluno";
import EditarAluno from "../pages/Forms/EditarAluno/editarAluno";
import Aluno from "../pages/Aluno/aluno";
import CadastrarEmpresa from "../pages/Forms/CadastrarEmpresa/cadastrarEmpresa";
import EditarEmpresa from "../pages/Forms/EditarEmpresa/editarEmpresa";
import EditarProfessor from "../pages/Forms/EditarProfessor/editarProfessor";
import SignIn from "../pages/Login/login";
import Lista from "../pages/Lista/lista";
import RouterAuth from "./routerAuth";
import Empresa from "../pages/Empresa/empresa";
import Professor from "../pages/Professor/professor";

const Rotas = () => (
  <BrowserRouter>
    <Header />
    <Routes>
      <Route
        path="/cadastro/aluno"
        caseSensitive={false}
        element={<CadastrarAluno />}
      />
      <Route
        path="/cadastro/empresa"
        caseSensitive={false}
        element={<CadastrarEmpresa />}
      />
      <Route
        path="/aluno/:id"
        caseSensitive={false}
        element={
          <RouterAuth>
            <Aluno />
          </RouterAuth>
        }
      />
      <Route
        path="/editar/aluno/:id"
        caseSensitive={false}
        element={
          <RouterAuth>
            <EditarAluno />
          </RouterAuth>
        }
      />
      <Route
        path="/empresa/:id"
        caseSensitive={false}
        element={
          <RouterAuth>
            <Empresa />
          </RouterAuth>
        }
      />
      <Route
        path="editar/empresa/:id"
        caseSensitive={false}
        element={
          <RouterAuth>
            <EditarEmpresa />
          </RouterAuth>
        }
      />
      <Route
        path="/professor/:id"
        caseSensitive={false}
        element={
          <RouterAuth>
            <Professor />
          </RouterAuth>
        }
      />
      <Route
        path="editar/professor/:id"
        caseSensitive={false}
        element={
          <RouterAuth>
            <EditarProfessor />
          </RouterAuth>
        }
      />
      <Route path="/login" caseSensitive={false} element={<SignIn />} />
      <Route
        path="/dashboard"
        caseSensitive={false}
        element={
          <RouterAuth>
            <Lista />
          </RouterAuth>
        }
      />
      <Route path="/" element={<Navigate to="/login" />} />
    </Routes>
  </BrowserRouter>
);

export default Rotas;
