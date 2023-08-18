/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    screens: {
        sm: { min: "390px", max: "819px" },
        md: { min: "820px", max: "1023px" },
        lg: { min: "1080px" },
        vv: { min: "980px" },
        vx: { max: "980px" },
        min1264: {min:"1264px"},
        nm9801264: {min:"980px", max:"1264px"},
        nm640980: {min:"640px", max:"980px"}
    },
},
  plugins: [],
}

