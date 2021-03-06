import React, { Component } from "react";
import { Navbar } from "react-bootstrap";


class Header extends Component {
  constructor(props) {
    super(props);
    this.state = {
      sidebarExists: false
    };
  }
  mobileSidebarToggle(e) {
    if (this.state.sidebarExists === false) {
      this.setState({
        sidebarExists: true
      });
    }
    e.preventDefault();
    document.documentElement.classList.toggle("nav-open");
    var node = document.createElement("div");
    node.id = "bodyClick";
    node.onclick = function() {
      this.parentElement.removeChild(this);
      document.documentElement.classList.toggle("nav-open");
    };
    document.body.appendChild(node);
  }
  render() {
    return (
      <Navbar fluid>
        <Navbar.Header>
          <Navbar.Brand>
            <a href="#">CMAD Management Station</a>
          </Navbar.Brand>
		</Navbar.Header>
		
		  
                              
        
        
      </Navbar>
    );
  }
}

export default Header;
