/* eslint-disable jsx-a11y/alt-text */
import { useEffect, useState } from "react";
import { useParams, useNavigate, Link } from "react-router-dom";
import { getId, isAluno, isEmpresa } from "../../services/auth";
import api from "../../services/service";

function Empresa() {
  const [empresa, setEmpresa] = useState({});

  const { id } = useParams();
  const myAccount = getId() === id && isEmpresa();

  const [formData, setFormData] = useState({
    empresaId: id,
    valor: null,
    descricao: "",
    imagem: "",
  });

  const navigate = useNavigate();

  function handleClick(event) {
    event.preventDefault();
    api.delete(`/professor/deletar/id/${id}`).then(() => navigate("/login"));
  }

  useEffect(() => {
    api.get(`/empresa/mostrar/id/${id}`).then((res) => {
      setEmpresa(res.data);
    });
  }, [id]);

  function handleChange(event) {
    const { name, value } = event.target;
    setFormData((prevFormData) => {
      return {
        ...prevFormData,
        [name]: value,
      };
    });
  }

  function handleSubmit(event) {
    event.preventDefault();
    api
      .post("/vantagem/cadastrar", formData)
      .then(() => window.location.reload(true));
  }

  async function handleCompra(vantagemID, vantagemValor) {
    try {
      await api.post("/compra/cadastrar", {
        alunoId: getId(),
        vantagensIds: [vantagemID],
        empresaId: id,
        valor: vantagemValor
      });
      alert("Compra realizada!");
    } catch (err) {
      alert(err.response.data);
    }
  }

  async function handleDelete(vantagemID) {
    try {
      await api.delete(`/aluno/deletar/id/${vantagemID}`);
      window.location.reload(true);
    } catch (err) {
      alert(err.response.data);
    }
  }

  const vantagens = empresa.vantagems !== undefined ? empresa.vantagems : [];
  return (
    <div className="container mt-5 mb-5">
      <div className="card" style={{ backgroundColor: "rgba(255, 255, 255, 0.8)" }}>
        <div className="card-body">
          <h1 className="card-title ">Empresa {empresa.nome}</h1>
          <table className="table table-bordered">
            <tbody>
              <tr>
                <th className="bg-secondary text-white">Nome:</th>
                <td>{empresa.nome}</td>
              </tr>
              <tr>
                <th className="bg-secondary text-white">CNPJ:</th>
                <td>{empresa.cnpj}</td>
              </tr>
              <tr>
                <th className="bg-secondary text-white">Email:</th>
                <td>{empresa.email}</td>
              </tr>
            </tbody>
          </table>

          <h2>Vantagens</h2>
          {vantagens.length > 0 ? (
            <table className="table">
              <caption>Vantagens cadastradas no sistema</caption>
              <thead>
                <tr>
                  <th className="bg-secondary text-white"></th>
                  <th className="bg-secondary text-white">Descricao</th>
                  <th className="bg-secondary text-white">Valor</th>
                  {isAluno() && <th scope="row" className="bg-secondary text-white">Ações</th>}
                </tr>
              </thead>
              <tbody>
                {vantagens.map((vantagem) => (
                  <tr key={vantagem.id}>
                    <th scope="row">
                      <img
                        src={
                          vantagem.imagem
                            ? vantagem.imagem
                            : "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/cute-cat-photos-1593441022.jpg"
                        }
                        width="100"
                      />
                    </th>
                    <th scope="row">{vantagem.descricao}</th>
                    <th scope="row">{vantagem.valor}</th>
                    {isAluno() && (
                      <th scope="row" >
                        <button
                          type="button"
                          onClick={() => {
                            handleCompra(vantagem.id, vantagem.valor);
                          }}
                          className="btn btn-primary"
                        >
                          Comprar
                        </button>
                      </th>
                    )}
                    {/* {myAccount && (
                      <th scope="row">
                        <button
                          type="button"
                          onClick={() => {
                            handleDelete(vantagem.id);
                          }}
                          className="btn btn-danger"
                        >
                          Deletar
                        </button>
                      </th>
                    )} */}
                  </tr>
                ))}
              </tbody>
            </table>
          ) : (
            "Nao existem vantagens cadastradas"
          )}

          {myAccount && (
            <>
              <h3>Cadastrar Vantagem</h3>
              <form onSubmit={handleSubmit}>
                <input
                  type="text"
                  placeholder="Valor"
                  onChange={handleChange}
                  name="valor"
                  value={formData.valor}
                  className="form-control"
                />
                <input
                  type="text"
                  placeholder="Descricao"
                  onChange={handleChange}
                  name="descricao"
                  value={formData.descricao}
                  className="form-control"
                />
                <input
                  type="text"
                  placeholder="URL Imagem"
                  onChange={handleChange}
                  name="imagem"
                  value={formData.imagem}
                  className="form-control"
                />
                <button type="submit" className="btn btn-primary mb-2">
                  Enviar
                </button>
              </form>
              <div>
                <button onClick={(e) => handleClick(e)} className="btn btn-danger mx-2">
                  Deletar
                </button>
                <Link to={`/editar/empresa/${id}`} className="btn btn-primary">
                  Editar Empresa
                </Link>
              </div>
            </>
          )}
        </div>
      </div>
    </div>
  );
}

export default Empresa;
