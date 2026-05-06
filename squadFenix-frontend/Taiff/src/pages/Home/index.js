import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import img from './logotaiff.png'
import { Link } from 'react-router-dom';

import './Home.css'

const Home = () => {
    return(
        <div>
            <main>
                <div className="container">
                    <div className="inicio">
                    <img id="img" src={img}/>
                        <div className="links">
                                <Link className="link" to="/cadZeroPeca">Novo Teste</Link>  
                                <Link className="link" to="/tabela">Lista de Testes</Link>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    );
}
//Antes do npm start, coloque esse comando $env:NODE_OPTIONS="--openssl-legacy-provider"
export default Home;