import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../../services/service";

function Aluno() {
  const [aluno, setAluno] = useState({});

  const { id } = useParams();

  function handleClick(event) {
    event.preventDefault();
    api
      .delete(`/aluno/deletar/id/${id}`)
      .then(() => (window.location.href = `/`));
  }

  useEffect(() => {
    api.get(`/aluno/mostrar/id/${id}`).then((res) => setAluno(res.data));
  }, [id]);

  return (
    <>
      <div style={{ textAligment: "center" }}>
        <h1>Aluno</h1>
        <p>{aluno.nome}</p>
        <button onClick={(e) => handleClick(e)}> Deletar </button>
        <a href={`/editar/aluno/${id}`}>Editar Aluno</a>
      </div>
    </>
  );
}

export default Aluno;
