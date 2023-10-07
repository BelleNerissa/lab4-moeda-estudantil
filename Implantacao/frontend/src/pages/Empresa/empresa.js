import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../../services/service";

function Empresa() {
  const [empresa, setEmpresa] = useState({});

  const { id } = useParams();

  function handleClick(event) {
    event.preventDefault();
    api
      .delete(`/aluno/deletar/id/${id}`)
      .then(() => (window.location.href = `/`));
  }

  useEffect(() => {
    api.get(`/empresa/mostrar/id/${id}`).then((res) => setEmpresa(res.data));
  }, [id]);

  return (
    <>
      <div style={{ textAligment: "center" }}>
        <h1>Empresa</h1>
        <p>{empresa.cnpj}</p>
        <button onClick={(e) => handleClick(e)}> Deletar </button>
        <a href={`/editar/empresa/${id}`}>Editar Empresa</a>
      </div>
    </>
  );
}

export default Empresa;
