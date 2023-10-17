import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { loginAuth } from "../../services/auth";
import api from "../../services/service";
import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap CSS

export default function SignIn() {
  const [formData, setFormData] = React.useState({
    email: "",
    senha: "",
  });

  const navigate = useNavigate();

  function handleChange(event) {
    const { name, value } = event.target;
    setFormData((prevFormData) => {
      return {
        ...prevFormData,
        [name]: value,
      };
    });
  }

  async function handleSubmit(event) {
    event.preventDefault();
    try {
      const res = await api.post("/login", formData);
      console.log(res.data);
      loginAuth(res.data.id, res.data.roleID);

      navigate("/dashboard");
      console.log(res.data);
    } catch (err) {
      alert(err.response.data);
    }
  }

  return (
    <>
      <div className="container pt-5 mt-5">
        <div className="row justify-content-center">
          <div className="col-md-6">
            <div className="card p-4" style={{ backgroundColor: "rgba(255, 255, 255, 0.8)" }}>
              <h2 className="text-center mb-4">Bem-vindo ao Sistema</h2>
              <form onSubmit={handleSubmit} className="col-md-12">
                <div className="mb-3">
                  <input
                    type="email"
                    className="form-control col-12"
                    placeholder="Email"
                    onChange={handleChange}
                    name="email"
                    value={formData.email}
                    required
                  />
                </div>
                <div className="mb-3">
                  <input
                    type="password"
                    className="form-control col-12"
                    placeholder="Senha"
                    onChange={handleChange}
                    name="senha"
                    value={formData.senha}
                    required
                  />
                </div>
                <button className="btn btn-primary btn-block">Entrar</button>
              </form>
              <div className="mt-3 text-center">
                <Link to="/cadastro/aluno" className="btn btn-link">
                  Fazer cadastro como Aluno
                </Link>
                <span className="mx-2">ou</span>
                <Link to="/cadastro/empresa" className="btn btn-link">
                  Fazer cadastro como Empresa
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
