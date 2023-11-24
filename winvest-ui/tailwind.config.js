/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}",], theme: {
      extend: {
          dropShadow: {
              'custom': '0px 5px 2px rgba(0, 0, 0, 0.40)',
          }
      },
  }, plugins: [],
}

