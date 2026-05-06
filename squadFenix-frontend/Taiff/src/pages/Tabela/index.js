import { Component } from 'react';
import Table from 'react-bootstrap/Table'
import 'bootstrap/dist/css/bootstrap.min.css';
import api from '../../api';
import Header from '../../Header'
import Footer from '../../Footer'

import './Tabela.css';

class Posicoes extends Component{

    state = {
      lista: [],
    }
    async componentDidMount(){
      const response = await api.get('/testes');
      this.setState({lista : response.data})
    };
    render(){
      const {lista} = this.state;

      return(   

      <div className ="container">

         <Header/>
        
        <h2 className="title">Lista dos Testes</h2>
        {
            lista.map((list) => (    
        <Table className="tabela" striped bordered hover variant="dark">
          <thead>
            <tr>
              <th className="titulos">ID</th>
              <th className="titulos">Nome</th>
              <th className="titulos">Modelo</th>
              <th className="titulos">Data</th>
              <th></th>
              {
              }
            </tr>
          </thead>
          <tbody>

              <tr key = {list.id}>
                <td>{list.id}</td>
                <td>{list.nome}</td>
                <td>{list.modelo}</td>
                <td>{list.dataTeste}</td>
                <td></td>
              </tr>

              <tr>
                <th className="titulos">Zero Peça</th>
              </tr>
              <tr>
                <th>Eixo X</th>
                <th>Eixo Y</th>
                <th>Eixo Z</th>
                <th>Rotação</th>
                <th></th>
              </tr>

              <tr>
                <td>{list.zeroPeca.x}</td>
                <td>{list.zeroPeca.y}</td>
                <td>{list.zeroPeca.z}</td>
                <td>{list.zeroPeca.r}</td>
                <td></td>
              </tr>

              <tr>
                <th className="titulos">Posiçoes</th>
              </tr>
              <tr>
                <th>Eixo X</th>
                <th>Eixo Y</th>
                <th>Eixo Z</th>
                <th>Rotação</th>
                <th>Tempo</th>
              </tr>

              {list.posicoes.map((posicao) =>(
                  <tr key={posicao.id}>
                  <td>{posicao.eixoX}</td>
                  <td>{posicao.eixoY}</td>
                  <td>{posicao.eixoZ}</td>
                  <td>{posicao.rotacao}</td>
                  <td>{posicao.tempo}</td>
                </tr>
              ))}
          </tbody>
  
          </Table>
           ))
          }
          <Footer/> 
          
      </div>    
      
      );
    }
    
  }

  export default Posicoes;