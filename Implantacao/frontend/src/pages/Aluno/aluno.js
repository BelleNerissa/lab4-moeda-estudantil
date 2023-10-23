import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { getId, isAluno, isProfessor } from "../../services/auth";
import api from "../../services/service";

function Aluno() {
  const [aluno, setAluno] = useState({});
  const [transacoes, setTransacoes] = useState({});

  const { id } = useParams();
  const myAccount = getId() === id && isAluno();

  function handleClick(event) {
    event.preventDefault();
    api
      .delete(`/aluno/deletar/id/${id}`)
      .then(() => (window.location.href = `/`));
  }

  const [formData, setFormData] = useState({
    alunoId: null,
    professorId: getId(),
    valor: null,
    descricao: "",
  });

  function handleChange(event) {
    const { name, value } = event.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  }

  async function handleSubmit(event) {
    event.preventDefault();
    try {
      const res = await api.post("/transacao/cadastrar/byprofessor", formData);
      alert(
        `Transacao realizada no valor de ${res.data.valor} moedas para o aluno ${aluno.nome}!`
      );
    } catch (err) {
      alert(err);
      console.log(err);
    }
  }
  useEffect(() => {
    api.get(`/aluno/mostrar/id/${id}`).then((res) => {
      setAluno(res.data);
      setFormData((prevFormData) => ({
        ...prevFormData,
        alunoId: res.data.id,
      }));
    });

    api.get(`/transacao/listar/aluno?id=${id}`).then((res) => setTransacoes(res.data));
  }, [id]);


  const compras = aluno.compras !== undefined ? aluno.compras : [];

  return (
    <div className="container mt-5">
      <div className="card" style={{ backgroundColor: "rgba(255, 255, 255, 0.8)" }}>
        <div className="card-body">
          <div >
            <h1>Aluno</h1>
            <table className="table table-bordered">
              <tbody>
                <tr>
                  <th className="bg-secondary text-white">Nome:</th>
                  <td>{aluno.nome}</td>
                </tr>
                <tr>
                  <th className="bg-secondary text-white">Email:</th>
                  <td>{aluno.email}</td>
                </tr>
                {myAccount && (
                  <>
                    <tr>
                      <th className="bg-secondary text-white">CPF:</th>
                      <td>{aluno.cpf}</td>
                    </tr>
                    <tr>
                      <th className="bg-secondary text-white">RG:</th>
                      <td>{aluno.rg}</td>
                    </tr>
                    <tr>
                      <th className="bg-secondary text-white">Endereco:</th>
                      <td>{aluno.endereco}</td>
                    </tr>
                    <tr>
                      <th className="bg-secondary text-white">Saldo:</th>
                      <td>{aluno.saldo}</td>
                    </tr>
                  </>
                )}
              </tbody>
            </table>
            {(myAccount || isProfessor()) && (
              <>
                <h2>Extrato</h2>
                <div className="mb-4">
                  <h3>Transações</h3>
                  {transacoes.length > 0 ? (
                    <>
                      <table className="table table-bordered">
                        <caption>Transacoes realizadas</caption>
                        <thead>
                          <tr>
                            <th>Professor</th>
                            <th>Quantidada de moedas</th>
                            <th>Motivacao</th>
                          </tr>
                        </thead>
                        <tbody>
                          {transacoes.map((transacao, index) => (
                            <tr key={index}>
                              <th scope="row">
                                <Link to={`/professor/${transacao.idProfessor}`}>
                                  {transacao.nomeProfessor}
                                </Link>
                              </th>
                              <td>{transacao.valor}</td>
                              <td>
                                {transacao.descricao
                                  ? transacao.descricao
                                  : "Sem descricao"}
                              </td>
                            </tr>
                          ))}
                        </tbody>
                      </table>
                    </>
                  ) : (
                    "Ainda não foram realizadas transações"
                  )}
                </div>
                {console.log(compras)}
                {console.log(transacoes)}
                <div>
                  <h3>Compras</h3>
                  {compras.length > 0 ? (
                    <>
                      <table className="table table-bordered">
                        <caption>Compras realizadas</caption>
                        <thead>
                          <tr>
                            <th></th>
                            <th>Produto</th>
                            <th>Valor</th>
                          </tr>
                        </thead>
                        <tbody>
                          {compras.map((compra, index) => (
                            <tr key={index}>
                              <th scope="row">
                                <img
                                  src={
                                    compra.vantagens[0].imagem
                                      ? compra.vantagens[0].imagem
                                      : "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/cute-cat-photos-1593441022.jpg"
                                  }
                                  width="100"
                                  alt=""
                                />
                              </th>
                              <th scope="row">{compra.vantagens[0].descricao}</th>
                              <td>{compra.valor}</td>
                            </tr>
                          ))}
                        </tbody>
                      </table>
                    </>
                  ) : (
                    "Você ainda não comprou nada"
                  )}
                </div>
                <br></br>
                <div className="row">
                  <div className="d-flex justify-content-start">
                    <button onClick={(e) => handleClick(e)} className="btn btn-danger mx-2">Deletar Aluno</button>
                    <Link to={`/editar/aluno/${id}`}>
                      <button className="btn btn-primary">Editar Aluno</button>
                    </Link>
                  </div>
                </div>

              </>
            )}
            {isProfessor() && (
              <div className="centered-container">
                {" "}
                <h2>💸Enviar moedas💸</h2>
                <form onSubmit={handleSubmit}>
                  <div className="mb-3">
                    <input
                      type="text"
                      className="form-control"
                      placeholder="Valor"
                      onChange={handleChange}
                      name="valor"
                      value={formData.valor || ""}
                    />
                  </div>
                  <div className="mb-3">
                    <input
                      type="text"
                      className="form-control"
                      placeholder="Descricao"
                      onChange={handleChange}
                      name="descricao"
                      value={formData.descricao || ""}
                    />
                  </div>
                  <button type="submit" className="btn btn-success">Enviar</button>
                </form>
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

export default Aluno;
