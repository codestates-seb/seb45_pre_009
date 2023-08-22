import React from "react";
import "./Products.css";

const DropdownProducts = () => {
  return (
    <div className="products_container">
      <div className="products_box">
        <div className="box1">
          <div className="products1">Stack Overflow</div>
          <div className="products2">Public questions & answers</div>
        </div>
        <div className="box2">
          <div className="products1">Stack Overflow for Teams</div>
          <div className="products2">
            Where developers & techonolgists share private knowledge with
            coworkers
          </div>
        </div>
        <div className="box3">
          <div className="products1">Talent</div>
          <div className="products2">Build your employer brand</div>
        </div>
        <div className="box4">
          <div className="products1">Advertising</div>
          <div className="products2">Reach developers & techonologists worldwide</div>
        </div>
      </div>
      <div className="box5">
        <div className="products1">Labs</div>
        <div className="products2">The future of Collective knowledge sharing</div>
      </div>
      <div className="box6">About the company</div>
    </div>
  );
};

export default DropdownProducts;
