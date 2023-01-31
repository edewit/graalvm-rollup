import { rollup } from './src/main/resources/node_modules/@rollup/browser/dist/es/rollup.browser.js';

rollup({
  input: 'example.mjs',
  plugins: [
    {
      name: 'loader',
      resolveId(source) {
        return source;
      },
      load(id) {
        if (id.startsWith("."))
          return readFully("./src/main/resources/" + id);
        else
          // this is the only external dependency atm but we need some sort of mapping
          return readFully("./src/main/resources/node_modules/date-fns/format/index.js")
      }
    }
  ]
})
  .then(bundle => bundle.generate({ format: 'es' }))
  .then(({ output }) => console.log(output[0].code));