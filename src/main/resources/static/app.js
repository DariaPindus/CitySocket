import React, { Component }  from 'react';
import ReactDOM from 'react-dom';
// Needed for onTouchTap
// http://stackoverflow.com/a/34015469/988941

import LoginScreen from '/static/js/auth/Loginscreen';
class App extends Component {
    constructor(props){
        super(props);
        this.state={
            loginPage:[],
            uploadScreen:[]
        }
    }
    componentWillMount(){
        var loginPage =[];
        loginPage.push(<LoginScreen appContext={this}/>);
        this.setState({
            loginPage:loginPage
        })
    }
    render() {
        return (
            <div className="App">
                {this.state.loginPage}
                {this.state.uploadScreen}
            </div>
        );
    }
}

export default App;

ReactDOM.render(<App />, document.getElementById('react') );