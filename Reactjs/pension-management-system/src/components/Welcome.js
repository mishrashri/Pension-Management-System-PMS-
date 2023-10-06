import {Component} from 'react'
import axios from "axios";
import './Welcome.css';
import Tilt from 'react-parallax-tilt';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import MoneyBoy from '../Money.json';
import 'bootstrap/dist/css/bootstrap.min.css';
import Lottie from 'lottie-react';
import Passed from '../Passed.json';
import Failed from '../Failed.json';

class Welcome extends Component{
    constructor(){
        super();
        this.state={
            token:null,
            data:null,
            matchedUser:null,
            responseCode:null,
            pensionInput:false
        };
        this.login = this.login.bind(this);
        this.fetchingUser = this.fetchingUser.bind(this);
        this.matchUser = this.matchUser.bind(this);
        this.processPension = this.processPension.bind(this);
        this.pensionInputState = this.pensionInputState.bind(this);
        this.logout = this.logout.bind(this);
    }

    login()
    {
        let userName = document.getElementById("userName").value;
        let password = document.getElementById("password").value;
        if(userName == "")
        {
            toast.error("User name can't be blank.");
        }        
        else if(password == "")
        {
            toast.error("Password can't be blank.");
        }
        else
        {

        const res = axios.post('http://localhost:8400/auth/authenticate',  
                {
                    // "password": "$2a$10$BhBZRNJZKsGHc4K6oC64w.EQWT7mb2DcgBXT0CkJMBxJDLHIfkCkK",
                    // "userName": "admin" 
                    "password": password,
                    "userName":userName
                }
            ).then(res => {
                this.setState({token:res.data.token})
                
            }).catch((error) => {
            
                toast.error(
                    "OOPS!\n" + error.response.data.message, {
                    position:"top-center"
                });
                
            })
        }
                
    }

    logout(){
        this.setState({
            token:null,
            data:null,
            matchedUser:null,
            responseCode:null,
            pensionInput:false
        });
    }

    fetchingUser()
    {
        let aadhaar = parseInt(document.getElementById("aadhaar").value );
        console.log(aadhaar);
        console.log(typeof(aadhaar));
        // let aadhaar = 1234567890123;
        let res = axios.get('http://localhost:8080/pensionerdetailbyaadhaar', {params:{aadhaar:aadhaar}, headers: {"Authorization" : `Bearer ${this.state.token}`} })
            .then((res)=>{
                this.setState({
                    data:res.data
                     
                });
                console.log(res.data);
            })
            .catch( (error) => {
                        // console.log(error.response.status);
                        // alert("OOPS!\n"+error.response.data.message);
                        toast.error(
                            "OOPS!\n" + error.response.data.message, {
                            position:"top-center"
                        });
                        this.setState({data:null})
                    });
    }

    matchUser()
    {
        let aadhaar = (document.getElementById("aadhaar").value);
        console.log(aadhaar)
        let namee = document.getElementById("name").value;
        let dob = (document.getElementById("dob").value);
        console.log(dob)
        let pan = document.getElementById("pan").value;
        if(aadhaar == "")
        {
            toast.error("Aadhaar can't be blank")
        }
        else if(namee == "")
        {
            toast.error("Name can't be blank")
        }
        else if(dob == "")
        {
            toast.error("Choose dob")
        }
        else if(pan == "")
        {
            toast.error("Pan can't be blank")
        }
        else
        {
            aadhaar = parseInt(aadhaar)
            let pensiontype = ""
            let val = document.getElementsByName("pensionType");
            console.log(val.length)
                for(let i=0; i<val.length; i++)
                {
                    if(val[i].checked)
                    {
                        pensiontype = val[i].value;
                    }
                }
                console.log(pensiontype)
                if(pensiontype == "")
                {
                    toast.error("Please choose pension type")
                }
                else
                {
                // console.log(aadhaar);
                // console.log(typeof(aadhaar));
                // let aadhaar = 1234567890123;
                    let res = axios.get('http://localhost:8081/pensiondetail', {params:{name:namee,dob:dob, pan:pan, pensiontype:pensiontype, aadhaar:aadhaar}, headers: {"Authorization" : `Bearer ${this.state.token}`} })
                        .then((res)=>{
                            this.setState({
                                matchedUser:res.data
                                
                            });
                            console.log(res.data);
                        })
                        .catch( (error) => {
                                    // console.log(error.response.status);
                                    // alert("OOPS!\n"+error.response.data.message);
                                    toast.error(
                                        "OOPS!\n" + error.response.data.message, {
                                        position:"top-center"
                                    });
                                    
                                    // this.setState({data:null})
                                });
                }
                
        }
            
    }

    processPension()
    {
        let aadhaar = (document.getElementById("aadhaarClaim").value);
        let amount = (document.getElementById("pensionAmountClaim").value);
        let bankServiceCharge = (document.getElementById("bankServiceChargeClaim").value);
        if(aadhaar == "")
        {
            toast.error("Aadhaar can't be blank")
        }
        else if(amount == "")
        {
            toast.error("Pension amount can't be blank")
        }
        else if(bankServiceCharge == "")
        {
            toast.error("Bank service charge can't be blank")
        }
        else
        {
            aadhaar = parseInt(aadhaar)
            amount = parseFloat(amount)
            bankServiceCharge = parseFloat(bankServiceCharge)
            const res = axios.post('http://localhost:8081/processpension',  
                    {
                        // "password": "$2a$10$BhBZRNJZKsGHc4K6oC64w.EQWT7mb2DcgBXT0CkJMBxJDLHIfkCkK",
                        // "userName": "admin" 
                        "aadhaar": aadhaar,
                        "pensionAmount":amount,
                        "bankServiceCharge":bankServiceCharge
                    },
                    {headers: {"Authorization" : `Bearer ${this.state.token}`}}
                ).then(res => {
                    this.setState({responseCode:res.data})
                    console.log(res.data.response);
                    console.log(typeof(res.data.response));
                }).catch((error) => {
                
                        toast.error(
                            "OOPS!\n"+error.response.data.message)
                    
                })
        }
    }

    pensionInputState()
    {
        this.setState({pensionInput:true});
    }

    render(){
        
        if(this.state.token === null)
        {
            return(
                <div className="Container-login">
                    <Tilt>
                        <div className="Card-login">
                            <div className="Animation-login">
                                <Lottie animationData={MoneyBoy} />
                             </div>
                            <h2>Please login</h2>
                            <input id='userName' type="text" placeholder='Enter username'></input><br></br>
                            <input id='password' type="password" placeholder='Enter password'></input><br></br>
                            <button id="button-login" onClick={this.login}>Login</button>
                    </div>
                    </Tilt>
                    <ToastContainer/>
                </div>
            )
        }
        else
        {
            
            if(this.state.matchedUser == null)
            {
                return (
                    <div className="Container-getDetails">
                        <div className="logout-button">
                                <button onClick={this.logout}>Log Out</button>
                        </div>   
                        <div className="Card-getDetails">
                            <div>
                                <input id='name' type='text' placeholder='Enter pensioner name'></input><br></br>
                                <input id='aadhaar' type='number' placeholder='Enter aadhaar number'></input><br></br>
                                <input id='pan' type='text' placeholder='Enter pan '></input><br></br>
                                <input id='dob' type='date'></input><br></br>
                                <p>Choose Pension type</p>
                                <div className="selfandfamily">
                                    <label htmlFor='Self'>Self</label>
                                    <input  type="radio" name="pensionType" value="self"></input>
                                    <label htmlFor='Family'>Family</label>
                                    <input type="radio"  name="pensionType" value="family"></input>
                                </div>
                                <button onClick={this.matchUser}>Get Details</button>
                                {/* <p>{this.state.token}</p> */}
                            </div>
                        </div>
                        <ToastContainer/>
                    </div>
                )
                
            }
            else if(this.state.responseCode == null && !this.state.pensionInput)
            {
                return(
                    <div className="process-pension-table">
                        <div className="logout-button">
                            <button onClick={this.logout}>Log Out</button>
                    </div> 
                        <table className="table table-striped table-dark">
                        <thead className="thead-light">
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Name</th>
                                <th scope="col">Pan</th>
                                <th scope="col">DOB</th>
                                <th scope="col">Pension Type</th>
                                <th scope="col">Pension Amount</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                               <td>{this.state.matchedUser.id}</td>
                               <td>{this.state.matchedUser.name}</td>
                               <td>{this.state.matchedUser.pan}</td>
                               <td>{this.state.matchedUser.dob}</td>
                               <td>{this.state.matchedUser.pensionType}</td>
                               <td>{this.state.matchedUser.pensionAmount}</td>
                        
                            </tr>
                        </tbody>
                    </table>
                    <div  className="process-pension">
                        <button onClick={this.pensionInputState}>Process Pension</button>
                    </div>
                    <ToastContainer/>
                    </div>);
                }
                else if(this.state.responseCode == null)
            {
                return(
                <div className="claim-pension-table">
                    <div className="logout-button">
                            <button onClick={this.logout}>Log Out</button>
                    </div> 
                <table className="table table-striped table-dark">
                        <thead className="thead-light">
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Name</th>
                                <th scope="col">Pan</th>
                                <th scope="col">DOB</th>
                                <th scope="col">Pension Type</th>
                                <th scope="col">Pension Amount</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                               <td>{this.state.matchedUser.id}</td>
                               <td>{this.state.matchedUser.name}</td>
                               <td>{this.state.matchedUser.pan}</td>
                               <td>{this.state.matchedUser.dob}</td>
                               <td>{this.state.matchedUser.pensionType}</td>
                               <td>{this.state.matchedUser.pensionAmount}</td>
                            </tr>
                        </tbody>
                        
                    </table><br></br>
                    <div className="Card-claim">
                        <input type='number' id='aadhaarClaim' placeholder='Enter aadhaar number'></input><br></br>
                        <input type= 'number' id='pensionAmountClaim' placeholder='Enter pension amount'></input><br></br>
                        <input type= 'number' id='bankServiceChargeClaim' placeholder='Enter bank service charge'></input><br></br>
                        <button onClick={this.processPension}>Claim</button>
                    </div>
                    <ToastContainer/>   
                    </div>);
            }
            else if(this.state.responseCode != null)
            {
                
                if(this.state.responseCode.response == 10)
                {
                    return(
                        <div className="Result-passed">
                            <div className="logout-button">
                            <button onClick={this.logout}>Log Out</button>
                            </div> 
                            <div className="Animation-passed">
                                <Lottie animationData={Passed} />
                             </div>
                        <p>Thank you, your pension has been processed.</p>
                        {/* <p>Response code {this.state.responseCode.response}</p> */}
                    </div>
                    );
                }
                else
                {
                    return(
                        <div className="Result-failed">
                            <div className="logout-button">
                            <button onClick={this.logout}>Log Out</button>
                            </div> 
                            <div className="Animation-failed">
                                <Lottie animationData={Failed} />
                             </div>
                        <p>OOPS! your pension can't be processed. Try again.</p>
                        {/* <p>Response code {this.state.responseCode.response}</p> */}
                    </div>
                    );
                }
                
            }
        }
    }
}
export {Welcome};