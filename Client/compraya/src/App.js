import logo from './logo.svg';
import './App.css';
import ListaUsuariosComponets from './Componetes/ListaUsuariosComponets';
import HeaderComponet from './Componetes/HeaderComponet';
import FooterComponet from './Componetes/FooterComponet';

function App() {
  return (
    <div>
      <HeaderComponet />
      <ListaUsuariosComponets />
      <FooterComponet />
    </div>

    // <div className="App">
    //   <header className="App-header">
    //     <img src={logo} className="App-logo" alt="logo" />
    //     <p>
    //       Edit <code>src/App.js</code> and save to reload.
    //     </p>
    //     <a
    //       className="App-link"
    //       href="https://reactjs.org"
    //       target="_blank"
    //       rel="noopener noreferrer"
    //     >
    //       Learn React
    //     </a>
    //   </header>
    // </div>
  );
}

export default App;
