import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { getId, isProfessor } from "../../services/auth";
import api from "../../services/service";

function Professor() {
  const [professor, setProfessor] = useState({});
  const [transacoes, setTransacoes] = useState({});

  const { id } = useParams();
  const myAccount = getId() === id && isProfessor();

  function handleClick(event) {
    event.preventDefault();
    api
      .delete(`/professor/deletar/id/${id}`)
      .then(() => (window.location.href = `/`));
  }

  useEffect(() => {
    api
      .get(`/professor/mostrar/id/${id}`)
      .then((res) => setProfessor(res.data));

    api
      .get(`/transacao/listar/professor?id=${id}`)
      .then((res) => setTransacoes(res.data));
  }, [id]);

  return (
    <div className="container mt-5">
      <div className="card" style={{ backgroundColor: "rgba(255, 255, 255, 0.6)" }}>
        <div className="card-body">
          <div className="centered-container">
            <h1>Professor</h1>
            <table className="table table-bordered">
              <tbody>
                <tr>
                  <th className="bg-secondary text-white">Nome:</th>
                  <td>{professor.nome}</td>
                </tr>
                <tr>
                  <th className="bg-secondary text-white">Email:</th>
                  <td>{professor.email}</td>
                </tr>
                {myAccount && (
                  <>
                    <tr>
                      <th className="bg-secondary text-white">CPF:</th>
                      <td>{professor.cpf}</td>
                    </tr>
                    <tr>
                      <th className="bg-secondary text-white">Moedas:</th>
                      <td>{professor.moedas}</td>
                    </tr>
                  </>
                )}
              </tbody>
            </table>
            <br></br>
            {myAccount && (
              <>
                <h2>Transacoes</h2>
                <div>
                  {transacoes.length > 0 ? (
                    <>
                      <table className="table table-bordered">
                        <caption>Transacoes realizadas</caption>
                        <thead>
                          <tr className="bg-secondary text-white">
                            <th>Aluno</th>
                            <th>Quantidada de moedas</th>
                          </tr>
                        </thead>
                        <tbody>
                          {transacoes.map((transacao, index) => (
                            <tr key={index}>
                              <th scope="row">
                                <Link to={`/aluno/${transacao.idAluno}`}>
                                  {transacao.nomeAluno}
                                </Link>
                              </th>
                              <td>{transacao.valor}</td>
                            </tr>
                          ))}
                        </tbody>
                      </table>
                    </>
                  ) : (
                    "Ainda não foram realizadas transações"
                  )}
                </div>{" "}
                <br></br>
                <div>
                  <button onClick={(e) => handleClick(e)} className="btn btn-danger"> Deletar </button>
                  <Link to={`/editar/professor/${id}`} className="btn btn-primary">Editar Professor</Link>
                </div>
              </>
            )}
          </div>
        </div></div>
    </div>
  );
}

export default Professor;
