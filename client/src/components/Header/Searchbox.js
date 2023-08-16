import './Header.css'


function Searchbox() {

    return (
      <div className="searchbox_container">
        <ul className="dropdown_box">
          <li className="option1">
             <span className="font1">[tag]</span>
             <span className="font2">search within a tag</span>
          </li>
          <li className="option2">
             <span className="font1">answers:0</span>
             <span className="font2">unanswered questions</span>
          </li>
          <li className="option1">
             <span className="font1">user:1234</span>
             <span className="font2">search by author</span>
          </li>
          <li className="option2">
             <span className="font1">score:3</span>
             <span className="font2">posts with a 3+ score</span>
          </li>
          <li className="option1">
             <span className="font1">"words here"</span>
             <span className="font2">sexact phrase</span>
          </li>
          <li className="option2">
             <span className="font1">is:question</span>
             <span className="font2">tpye of post</span>
          </li>
          <li className="option1">
             <span className="font1">collective:"Name"</span>
             <span className="font2">collective content</span>
          </li>
          <li className="option2">
             <span className="font1">isaccepted:yes</span>
             <span className="font2">search within status</span>
          </li>
        </ul>  
        <div className="searchbox_mini">
            <button className="button_ask">Ask a question</button>
            <span className="option_help">Search help</span>
        </div>
      </div>
    );
  }
  
  export default Searchbox;