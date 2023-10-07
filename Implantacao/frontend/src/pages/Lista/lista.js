import { useEffect, useState } from "react";
import api from "../../services/service";

function Lista() {
  const [alunos, setAlunos] = useState([{}]);
  const [empresas, setEmpresas] = useState([{}]);

  useEffect(() => {
    api.get("/aluno/listar").then((res) => setAlunos(res.data));
    api.get("/empresa/listar").then((res) => setEmpresas(res.data));
  }, []);

  return (
    <>
      <div style={{ textAligment: "center" }}>
        <h1>Alunos</h1>
        <div>
          {alunos.length > 0
            ? alunos.map((aluno) => (
                <>
                  <a href={`/aluno/:${aluno.id}`}>{aluno.nome}</a>
                  <div>{JSON.stringify(aluno)}</div>
                </>
              ))
            : "Nao existem alunos cadastrados"}
        </div>
        <h1>Empresas</h1>
        <div>
          {empresas.length > 0
            ? empresas.map((empresa) => (
                <>
                  <a href={`/empresa/:${empresa.id}`}>{empresa.cnpj}</a>
                  <div>{JSON.stringify(empresa)}</div>
                </>
              ))
            : "Nao existem empresas cadastrados"}
        </div>{" "}
      </div>
    </>
  );
}

export default Lista;
