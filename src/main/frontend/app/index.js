/*import React from 'react';
import ReactDOM from 'react-dom';
import '../style/style.css'

ReactDOM.render(
	<h1 className="testblue">App working</h1>,
	document.querySelector('.container'));*/
import React from 'react';
import ReactDOM from 'react-dom';
import App from 'app';
import { BrowserRouter as Router } from 'react-router-dom';

ReactDOM.render(
    <Router>
        <App />
    </Router>, 
    document.querySelector('.container')
);
