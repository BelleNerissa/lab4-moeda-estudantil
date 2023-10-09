import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import api from "../../services/service";
import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap CSS

function Lista() {
  const [alunos, setAlunos] = useState([]);
  const [empresas, setEmpresas] = useState([]);
  const [professores, setProfessores] = useState([]);

  useEffect(() => {
    api.get("/aluno/listar").then((res) => setAlunos(res.data));
    api.get("/empresa/listar").then((res) => setEmpresas(res.data));
    api.get("/professor/listar").then((res) => setProfessores(res.data));
  }, []);

  return (
    <div className="container mt-4">
      <div className="card" style={{ backgroundColor: "rgba(255, 255, 255, 0.8)" }}>
        <div className="card-body">
          <h1 className="card-title">Alunos</h1>
          <div className="table-responsive">
            {alunos.length > 0 ? (
              <>
                <table className="table table-bordered">
                  <caption>Alunos cadastrados no sistema</caption>
                  <thead className="table-dark">
                    <tr>
                      <th>Nome</th>
                      <th scope="col">Email</th>
                    </tr>
                  </thead>
                  <tbody>
                    {alunos.map((aluno) => (
                      <tr key={aluno.id}>
                        <th scope="row">
                          <Link to={`/aluno/${aluno.id}`}>{aluno.nome}</Link>
                        </th>
                        <td>{aluno.email}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </>
            ) : (
              "Nao existem alunos cadastrados"
            )}
          </div>

          <h1 className="card-title mt-4">Empresas</h1>
          <div className="table-responsive">
            {empresas.length > 0 ? (
              <>
                <table className="table table-bordered">
                  <caption>Empresas cadastradas no sistema</caption>
                  <thead className="table-dark">
                    <tr>
                      <th>Nome</th>
                      <th scope="col">CNPJ</th>
                    </tr>
                  </thead>
                  <tbody>
                    {empresas.map((empresa) => (
                      <tr key={empresa.id}>
                        <th scope="row">
                          <Link to={`/empresa/${empresa.id}`}>
                            {empresa.nome}
                          </Link>
                        </th>
                        <td>{empresa.cnpj}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </>
            ) : (
              "Nao existem empresas cadastradas"
            )}
          </div>

          <h1 className="card-title mt-4">Professores</h1>
          <div className="table-responsive">
            {professores.length > 0 ? (
              <>
                <table className="table table-bordered">
                  <caption>Professores cadastrados no sistema</caption>
                  <thead className="table-dark">
                    <tr>
                      <th>Nome</th>
                      <th scope="col">Email</th>
                    </tr>
                  </thead>
                  <tbody>
                    {professores.map((professor) => (
                      <tr key={professor.id}>
                        <th scope="row">
                          <Link to={`/professor/${professor.id}`}>
                            {professor.nome}
                          </Link>
                        </th>
                        <td>{professor.email}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </>
            ) : (
              "Nao existem professores cadastrados"
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

export default Lista;
