


function Searchbox() {

    return (
      <div className="absolute top-[100%] left-0 w-full bg-white border border-gray-300 border-solid p-2.5 z-10 mt-2.5 shadow-md shadow-gray-300 rounded-md">
        <ul className="bg-white text-[14px] p-[5px] break-all grid grid-cols-2">
          <li className="block list-none text-left p-[5px]">
             <span className="text-black pr-1.5">[tag]</span>
             <span className="text-gray-500">search within a tag</span>
          </li>
          <li className="text-left p-[5px] list-none">
             <span className="text-black pr-1.5">answers:0</span>
             <span className="text-gray-500">unanswered questions</span>
          </li>
          <li className="text-left p-[5px] list-none">
             <span className="text-black pr-1.5">user:1234</span>
             <span className="text-gray-500">search by author</span>
          </li>
          <li className="text-left p-[5px] list-none">
             <span className="text-black pr-1.5">score:3</span>
             <span className="text-gray-500">posts with a 3+ score</span>
          </li>
          <li className="text-left p-[5px] list-none">
             <span className="text-black pr-1.5">"words here"</span>
             <span className="text-gray-500">sexact phrase</span>
          </li>
          <li className="text-left p-[5px] list-none">
             <span className="text-black pr-1.5">is:question</span>
             <span className="text-gray-500">tpye of post</span>
          </li>
          <li className="text-left p-[5px] list-none">
             <span className="text-black pr-1.5">collective:"Name"</span>
             <span className="text-gray-500">collective content</span>
          </li>
          <li className="text-left p-[5px] list-none">
             <span className="text-black pr-1.5">isaccepted:yes</span>
             <span className="text-gray-500">search within status</span>
          </li>
        </ul>  
        <div className="bg-white border-t-gray-300 border-solid border-t-[1px] text-[13px break-all] flex justify-between items-center pt-1.5">
            <button className="h-[25px] w-[110px] text-xs rounded-md border-none bg-blue-50 text-sky-800 hover:bg-blue-100">Ask a question</button>
            <span className="text-right text-sky-700 p-[10px] text-[11px]">Search help</span>
        </div>
      </div>
    );
  }
  
  export default Searchbox;