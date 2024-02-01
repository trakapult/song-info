const {Song} = require('../models')
const {Op} = require('sequelize')

module.exports = {
    async index (req, res) {
        let song = null
        const search = req.query.search
        try {
            if (search) {
                console.log(search)
                // find all where search is in the title column
                song = await Song.findAll({ // await is necessary
                    where: {
                        [Op.or]: [
                            'title', 'artist', 'genre', 'album'
                        ].map(key => ({
                            [key]: {
                                [Op.like]: `%${search}%`
                            }
                        }))
                    }
                })
            } else {
                song = await Song.findAll({ // await is necessary
                    limit: 10
                })
            }
            res.send(song)
        } catch (err) {
            res.status(500).send({
                error: 'An error has occured trying to fetch the songs'
            })
        }
    },
    async show (req, res) {
        try {
            console.log(req.params.songId)
            const song = await Song.findByPk(req.params.songId) // await is necessary
            res.send(song)
        } catch (err) {
            res.status(500).send({
                error: 'An error has occured trying to fetch the song'
            })
        }
    },
    async post (req, res) {
        try {
            const song = await Song.create(req.body) // await is necessary
            res.send(song)
        } catch (err) {
            res.status(500).send({
                error: 'An error has occured trying to create the song'
            })
        }
    },
    async put (req, res) {
        try {
            const song = await Song.update(req.body, {
                where: {
                    id: req.params.songId
                }
            }) // await is necessary
            console.log(req.body)
        } catch (err) {
            res.status(500).send({
                error: 'An error has occured trying to update the song'
            })
        }
    }
}